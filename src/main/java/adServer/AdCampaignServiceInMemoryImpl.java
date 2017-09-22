package adServer;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("AdCampaignService")
public class AdCampaignServiceInMemoryImpl implements AdCampaignService {
	
	private List<AdCampaign> adCampaignList;
	// Define the logger object for this class
	  private final Logger log = LoggerFactory.getLogger(this.getClass());
	// static initializer for testing purposes
	{
		adCampaignList = new LinkedList<AdCampaign>();
		adCampaignList.add(new AdCampaign("Red Partner", 86400, "Buy Red's great products!", "Great Red Stuff", "AdStatus.Active"));
		adCampaignList.add(new AdCampaign("Green Partner", 10, "Buy Green's so-so products!", "So-so Green Stuff", "AdStatus.Active"));
		adCampaignList.add(new AdCampaign("Blue Partner", 86400, "Buy Blue's terrible products!", "Terrible Blue Stuff", "AdStatus.Active"));
	}

	@Override
	public AdCampaign createAdCampaign(AdCampaign adCampaign) {
		// check that it doesn't already exist. We're going to use
		// the partner_id, title, content, and duration as a compound
		// key since there wasn't a generated ID specified in the requirements.
		// This is totally inefficient in a list lookup; I'd use an RDBMS or
		// object store like CouchBase if doing this for real and use a generated
		// keyspace for partitioning/uniqueness. Optimizations not going to be
		// addressed in a coding exercise...
		AdCampaign result = null;
		Boolean exists = false;
		for (AdCampaign ac : adCampaignList ) {
			if (ac.getPartnerId().equals(adCampaign.getPartnerId()) 
					&& ac.getDuration() == adCampaign.getDuration()
					&& ac.getAdTitle().equals(adCampaign.getAdTitle())
					&& ac.getAdContent().equals(adCampaign.getAdContent()))
			{
				exists = true;
				break;
			}
		}
		if (!exists) {
			adCampaignList.add(adCampaign);
			result = adCampaign;
		}
		return result;
	}

	@Override
	public List<AdCampaign> getAdCampaignByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdCampaign> getAdCampaignByContent(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdCampaign> getAdCampaignByDuration(long duration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdCampaign> getAdCampaignByTitleAndDuration(String title, long duration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdCampaign updateAdCampaign(AdCampaign adCampaign) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdCampaign getActiveAdCampaign(String partnerId) {
		AdCampaign result;
		//List<AdCampaign> resultList = adCampaignList.stream()
		//	.filter(adCampaign -> 
		//	partnerId.equals(adCampaign.getPartnerId()) 
		//	&& (adCampaign.getAdStatus().equals(AdStatus.Active) )
		//	&& (adCampaign.getExpiration() < ( System.currentTimeMillis() / 1000 ) )
		//	)
		//	.collect(Collectors.toList());
		List<AdCampaign> resultList = new LinkedList<AdCampaign>();
		log.info("Number of ad campaigns in repository: " + adCampaignList.size());
		for (AdCampaign ac : adCampaignList ) {
			if (ac.getPartnerId().equals(partnerId) 
					&& ac.getAdStatus().equals(AdStatus.Active)
					&& ac.getDuration() > 0 )
			{
				resultList.add(ac);
			}
		}
		if (resultList.size() == 0) {
			result = null;
			log.info("No results found for partner_id: " + partnerId);
		}
		else if (resultList.size() == 1) {
			result = resultList.get(0);
		}
		else {
			result = null;
			// need to log this.
			log.info("Multiple results found for partner_id: " + partnerId);
		}
		return result;
		//AdCampaign result = adCampaignList.
		//return new AdCampaign("test-from-service-layer", 100, "content", "title", AdStatus.Active);
	}

}
