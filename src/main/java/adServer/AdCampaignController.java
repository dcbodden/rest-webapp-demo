package adServer;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<AdCampaign> getActiveAdCampaign(@RequestParam(value="partner_id", defaultValue="") String partnerId) {
		//return new AdCampaign("test", 100, "content", "title", AdStatus.Active);
		AdCampaign adCampaign = adCampaignService.getActiveAdCampaign(partnerId);
		if (adCampaign == null) {
    		return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<AdCampaign>(adCampaign, HttpStatus.OK);
		}
	}

    @RequestMapping(method=RequestMethod.POST, value="/adcampaign")
    public ResponseEntity<AdCampaign> CreateAdCampaign(@RequestBody AdCampaign adcampaign) {
    	AdCampaign newCampaign = adCampaignService.createAdCampaign(adcampaign);
    	if (newCampaign == null) {
    		return new ResponseEntity(HttpStatus.CONFLICT);
    	} else {
            return new ResponseEntity<AdCampaign>(newCampaign, HttpStatus.CREATED);
    	}
    }
}
