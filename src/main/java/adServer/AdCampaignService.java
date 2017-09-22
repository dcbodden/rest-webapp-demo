package adServer;

import java.util.List;

public interface AdCampaignService {
	AdCampaign createAdCampaign(AdCampaign adCampaign);
	
    List<AdCampaign> getAdCampaignByTitle(String title);
    List<AdCampaign> getAdCampaignByContent(String content);
    List<AdCampaign> getAdCampaignByDuration(long duration);
    List<AdCampaign> getAdCampaignByTitleAndDuration(String title, long duration);
    
    AdCampaign updateAdCampaign(AdCampaign adCampaign);
    
    AdCampaign getActiveAdCampaign(String partnerId);
    
}
