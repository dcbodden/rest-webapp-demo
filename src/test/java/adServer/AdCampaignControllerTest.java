package adServer;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestBody;

public class AdCampaignControllerTest {

	@Test
	public void testCreateAdCampaign() {
		String adCampaignJsonBody =
				"{ \n"
				+ "partner_id : 'goose01' \n"
				+ "duration : '86400' \n" 
				+ "ad_content : 'Hey buy my stuff as it is excellent' \n"
				+ "ad_title : 'AwesomeAd from Doug Bodden' \n"
				+ "ad_status : 'Active' \n"
				+ "}";
		AdCampaignController controller = new AdCampaignController();
		//AdCampaign test1 = controller.CreateAdCampaign(@RequestBody AdCampaign adCampaignJsonBody);
	}

}
