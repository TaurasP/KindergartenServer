//package lt.viko.eif.tpetrauskas.kindergarten;
//
//import java.util.Base64;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//
//public class GenerateJwtKey {
//    public static void main(String[] args) throws Exception {
//        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//        keyGen.init(256);
//        SecretKey secretKey = keyGen.generateKey();
//        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
//        System.out.println("Generated JWT Secret Key: " + encodedKey);
//    }
//}
//
