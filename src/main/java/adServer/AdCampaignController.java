package adServer;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdCampaignController {
	
	@Autowired
	private AdCampaignService adCampaignService;
	
	@RequestMapping(method=RequestMethod.GET, value="/adcampaign", params = "partner_id")
	public ResponseEntity<AdCampaign> getAdCampaign(@RequestParam(value="partner_id", defaultValue="") String partnerId) {
		
		AdCampaign adCampaign = adCampaignService.getActiveAdCampaign(partnerId);
		if (adCampaign == null) {
    		return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<AdCampaign>(adCampaign, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/adcampaign", params = "ad_title")
	public ResponseEntity<List<AdCampaign>> getAdCampaignByTitle(@RequestParam(value="ad_title") String title) {

		List<AdCampaign> adCampaigns = adCampaignService.getAdCampaignByTitle(title);
		if (adCampaigns.isEmpty() ) {
    		return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<AdCampaign>>(adCampaigns, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/adcampaign", params = "ad_content")
	public ResponseEntity<List<AdCampaign>> getAdCampaignByContent(@RequestParam(value="ad_content") String content) {

		List<AdCampaign> adCampaigns = adCampaignService.getAdCampaignByContent(content);
		if (adCampaigns.isEmpty() ) {
    		return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<AdCampaign>>(adCampaigns, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/adcampaign", params = {"ad_title", "duration"})
	public ResponseEntity<List<AdCampaign>> getAdCampaignByTitleAndDuration(
			@RequestParam(value="ad_title") String title,
			@RequestParam(value="duration") long duration) {

		List<AdCampaign> adCampaigns = adCampaignService.getAdCampaignByTitleAndDuration(title, duration);
		if (adCampaigns.isEmpty() ) {
    		return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<AdCampaign>>(adCampaigns, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/adcampaign", params = "duration")
	public ResponseEntity<List<AdCampaign>> getAdCampaignByDuration(@RequestParam(value="duration") long duration) {

		List<AdCampaign> adCampaigns = adCampaignService.getAdCampaignByDuration(duration);
		if (adCampaigns.isEmpty() ) {
    		return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<AdCampaign>>(adCampaigns, HttpStatus.OK);
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
