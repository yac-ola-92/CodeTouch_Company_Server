package com.tagmaster.codetouch.service.identity;

import com.tagmaster.codetouch.dto.APISignupDTO;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

@Service
public class AuthReqServiceImpl implements AuthService {
    private final String imp_key = "4562753284060201";
    //아임포트 관리자 페이지의 시스템설정->내정보->REST API Secret 값을 입력한다.
    private final String imp_secret = "M1AShrV1X6oXCY34M1N3Av3pm2uHRibcIjFfxzF1CxrKVDNqMJL9QL3fsZAsHVPGhmnT9wRy6VqOk5yz";
    String accessToken = "";
    //본인인증 모듈을 호출한 페이지에서 ajax로 넘겨받은 imp_uid값을 저장한다.
    @Override
    public String TokenService() throws Exception {

            // Step 1: 토큰 요청
            String tokenUrl = "https://api.iamport.kr/users/getToken";

            URL urlToToken = new URL(tokenUrl);
            HttpURLConnection conn = (HttpURLConnection) urlToToken.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            // 파라미터 세팅
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

            JSONObject requestData = new JSONObject();
            requestData.put("imp_key", imp_key);
            requestData.put("imp_secret", imp_secret);

            bw.write(requestData.toString());
            bw.flush();
            bw.close();

            int repCode = conn.getResponseCode();
            if (repCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONObject data = (JSONObject) jsonObject.get("response");

                accessToken = data.getString("access_token");
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                StringBuilder errResponse = new StringBuilder();
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    errResponse.append(inputLine);
                }
                br.close();

                System.out.println(errResponse.toString());
            }
            return accessToken;
        }
        @Override
        public APISignupDTO AuthReqService (String imp_uid, String accessToken) throws Exception {


            String certificationUrl = "https://api.iamport.kr/certifications/" + imp_uid;

            URL urlToCertification = new URL(certificationUrl);
            HttpURLConnection certificationConn = (HttpURLConnection) urlToCertification.openConnection();

            certificationConn.setRequestMethod("GET");
            certificationConn.setRequestProperty("Authorization", "Bearer " + accessToken);
            certificationConn.setRequestProperty("Accept", "application/json");

            int responseCode = certificationConn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(certificationConn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONObject data = (JSONObject) jsonObject.get("response");

                System.out.println(data.toString());
                APISignupDTO dto = new APISignupDTO();
                dto.setName(data.getString("name"));
                dto.setPhone(data.getString("phone"));
                dto.setBirth(LocalDate.parse(data.getString("birth")));
                dto.setGender(Integer.parseInt(data.getString("gender")));
                return dto;
            }
    return null;
    }
}


