package adServer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("AdCampaignService")
public class AdCampaignServiceInMemoryImpl implements AdCampaignService {
	
	private static final AtomicLong counter = new AtomicLong();
	private List<AdCampaign> adCampaignList;
	// Define the logger object for this class
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	// static initializer for testing purposes
	{
		//adCampaignId = 0; 	// obviously this would be in the datastore in 
							// a non-toy implementation.
		adCampaignList = new LinkedList<AdCampaign>();
		adCampaignList.add(new AdCampaign(counter.incrementAndGet(), "Red Partner", 86400, "Buy Red's great products!", "Great Red Stuff", AdStatus.Active));
		adCampaignList.add(new AdCampaign(counter.incrementAndGet(), "Green Partner", 10, "Buy Green's so-so products!", "So-so Green Stuff", AdStatus.Inactive));
		adCampaignList.add(new AdCampaign(counter.incrementAndGet(), "Blue Partner", 86400, "Buy Blue's terrible products!", "Terrible Blue Stuff", AdStatus.Active));

	}

	@Override
	public AdCampaign createAdCampaign(AdCampaign adCampaign) {
		AdCampaign result = adCampaign;
		for (AdCampaign savedCampaign : adCampaignList) {
			if (savedCampaign.getPartnerId().equals(adCampaign.getPartnerId())
				&& savedCampaign.getAdStatus().equals(AdStatus.Active)) {
				result = null;
				break;
			}
		}
		if (result != null) {
		adCampaign.setAdCampaignId(counter.incrementAndGet());
			adCampaignList.add(adCampaign);
		}

		return result;
	}

	@Override
	public List<AdCampaign> getAdCampaignByTitle(String title) {
		List<AdCampaign> result = new LinkedList<AdCampaign>();
		for (AdCampaign ac : adCampaignList) {
			if (ac.getAdTitle().equals(title)) {
				result.add(ac);
			}
		}
		return result;
	}

	@Override
	public List<AdCampaign> getAdCampaignByTitleAndDuration(String title, long duration) {
		List<AdCampaign> result = new LinkedList<AdCampaign>();
		for (AdCampaign ac : adCampaignList) {
			if (ac.getAdTitle().equals(title) && (ac.getDuration() == duration)) {
				result.add(ac);
			}
		}
		return result;
	}
	
	@Override
	public List<AdCampaign> getAdCampaignByContent(String content) {
		List<AdCampaign> result = new LinkedList<AdCampaign>();
		for (AdCampaign ac : adCampaignList) {
			if (ac.getAdContent().equals(content)) {
				result.add(ac);
			}
		}
		return result;
	}

	@Override
	public List<AdCampaign> getAdCampaignByDuration(long duration) {
		List<AdCampaign> result = new LinkedList<AdCampaign>();
		for (AdCampaign ac : adCampaignList) {
			if (ac.getDuration() == duration) {
				result.add(ac);
			}
		}
		return result;
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
					&& ac.getExpiration() > (System.currentTimeMillis()/1000) )
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
