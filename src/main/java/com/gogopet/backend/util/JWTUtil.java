package com.gogopet.backend.util;

import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JWTUtil {

	@Value("classpath:public.key")
	private Resource publicKeyFile;
	@Value("classpath:private.key")
	private Resource privateKeyFile;
	
	private int expireDays = 7;

	public String createToken(String claim) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		//IssueDate and ExpireDate
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		cal.add(Calendar.DATE, expireDays);
		
		return JWT.create()
//				.withHeader(null)
				.withClaim("claim", claim)
				.withIssuedAt(now)
				.withExpiresAt(cal.getTime())
				.sign(getAlogrithm());
	}

	public String parseToken(String token) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		JWTVerifier verifier = JWT.require(getAlogrithm()).build();
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getClaim("claim").asString();
	}

	private Algorithm getAlogrithm() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.getFile().toPath());
		byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.getFile().toPath());

		KeyFactory kf = KeyFactory.getInstance("RSA");

		RSAPublicKey publicKey = (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
		RSAPrivateKey privateKey = (RSAPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
		return Algorithm.RSA512(publicKey, privateKey);
	}
}
