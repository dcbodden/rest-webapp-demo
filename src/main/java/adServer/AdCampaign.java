package adServer;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AdCampaign {
	
	//"partner_id": "unique_string_representing_partner',
//		"duration": "int_representing_campaign_duration_in_seconds_from_now"/
//		"ad_content": "string_of_content_to_display_as_ad",
	//	"ad_title": "string_of_title_to_display",
		//"ad_status": “string_of_status_indicating_Active_or_Inactive”
	
    private final String 	partnerId;
    private final long 		duration;
    private final String 	adContent;
    private final String	adTitle;
    private final AdStatus	adStatus;
    @JsonIgnore
    private final long		expiration;

    
    public AdCampaign(String partnerId, long duration, String ad_content, String ad_title, AdStatus ad_status) {
        this.partnerId = partnerId;
        this.duration = duration;
        this.adContent = ad_content;
        this.adTitle = ad_title;
        this.adStatus = ad_status;
        this.expiration = (System.currentTimeMillis() / 1000) + duration;
    }

    public String getPartnerId() {
        return partnerId;
    }
    
    public long getDuration() {
    	return expiration - ( System.currentTimeMillis() / 1000 );
    }

    public String getContent() {
        return adContent;
    }
    
    public String getTitle() {
    	return adTitle;
    }
    
    public AdStatus getAdStatus() {
    	return adStatus;
    }
    
    public long getExpiration() {
    	return expiration;
    }
}
