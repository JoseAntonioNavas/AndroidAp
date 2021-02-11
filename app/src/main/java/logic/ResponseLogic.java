package logic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResponseLogic {


        private Integer status;
        private String msg;

        public ResponseLogic(Integer status,String msg) {

            this.status = status;
            this.msg = msg;

        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }


        @Override
        public String toString() {
            return "ErrorLogic [status=" + status + ", msg=" + msg + "]";
        }
        public static List<ResponseLogic> JsonToErrores(String response) throws JSONException {
            List<ResponseLogic> lstResultado = new ArrayList<>();


            JSONArray jsonA = new JSONArray(response);
            for (int i = 0; i < jsonA.length(); i++) {

                JSONObject jsonO = jsonA.getJSONObject(i);
                ResponseLogic u = JsonToError(jsonO);
                lstResultado.add(u);
            }

            return lstResultado;
        }

        public static ResponseLogic JsonToError(JSONObject jsonO) throws JSONException {


            Integer status = jsonO.getInt("status");
            String msg = jsonO.getString("msg");

            return new ResponseLogic(status,msg);

        }


}
