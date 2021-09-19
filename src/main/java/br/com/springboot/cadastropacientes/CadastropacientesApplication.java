package br.com.springboot.cadastropacientes;

//import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CadastropacientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastropacientesApplication.class, args);

//		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
//		encryptor.setPassword("9592f001-7c7a-4182-8aa5-04301cc41f9b");
//		encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
////		String encryptedText = encryptor.encrypt("45947800080");
//
//
//		String plainText = encryptor.decrypt("nInkLqSy61nc1jCqXqAXjEva3Sbhz0tj");
////		System.out.println(encryptedText);
//		System.out.println(plainText);

	}

}
