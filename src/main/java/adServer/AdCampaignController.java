package adServer;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdCampaignController {
	
	@Autowired
	private AdCampaignService adCampaignService;
	
	@RequestMapping(method=RequestMethod.GET, value="/adcampaign")
	public AdCampaign getActiveAdCampaign(@RequestParam(value="partner_id", defaultValue="") String partnerId) {
		//return new AdCampaign("test", 100, "content", "title", AdStatus.Active);
		return adCampaignService.getActiveAdCampaign(partnerId);
	}

    @RequestMapping(method=RequestMethod.POST, value="/adcampaign")
    public AdCampaign CreateAdCampaign(@RequestBody AdCampaign adcampaign) {
        return adCampaignService.createAdCampaign(adcampaign);
    }
}
