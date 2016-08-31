package logic;

import java.io.File;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Media;
import facebook4j.PhotoUpdate;
import facebook4j.auth.AccessToken;

public class Publisher {
	private static String appId = "274842262897183";
	private static String appSecret = "a5bcdcde4b8a26fffeaac88b0351c017";
	private static String accessToken="EAAD597NzAh8BAM8NZAi5XNUdlvO9eNHpLxWLG74feqXBowfwEt4noGZBDw1NpyZAJh60FxFihKnLKj6oqkYolp7T0ZBcBPByZAZB5Si4IycY0zZCEmw2kUSBYFvbAy9fZAOkdtNYdIUGiEHNgBXEfu4A9BIInYqSctMZD";

	public static void publicar(String foto, String comentario )throws FacebookException {
		
		Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId(appId, appSecret);
		facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
		Media source = new Media(new File(foto));
		PhotoUpdate pu = new PhotoUpdate(source);
		pu.setMessage(comentario);
		facebook.postPhoto(pu);
	}

}
