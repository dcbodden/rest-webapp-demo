package adServer;

import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AdCampaign {
	
	//"partner_id": "unique_string_representing_partner',
//		"duration": "int_representing_campaign_duration_in_seconds_from_now"/
//		"ad_content": "string_of_content_to_display_as_ad",
	//	"ad_title": "string_of_title_to_display",
		//"ad_status": “string_of_status_indicating_Active_or_Inactive”
	private long 		adCampaignId;
	@JsonProperty(value="partner_id")
    private String 		partnerId;
    private long 		duration;
    private String 		adContent;
    private String		adTitle;
    private AdStatus	adStatus;
    private long		expiration;

    
    public AdCampaign() {

    }
    
    public AdCampaign(long adCampaignId, String partner_id, long duration, String ad_content, String ad_title, AdStatus ad_status) {
    	this.adCampaignId = adCampaignId;
    	this.partnerId = partner_id;
        this.duration = duration;
        this.adContent = ad_content;
        this.adTitle = ad_title;
        this.adStatus = ad_status;
        //this.adStatus = AdStatus.Active;
        this.expiration = (System.currentTimeMillis() / 1000) + duration;
    }
    
    public long getAdCampaignId() {
    	return this.adCampaignId;
    }
    
    public void setAdCampaignId(long adCampaignId ) {
    	this.adCampaignId = adCampaignId;
    }

    public String getPartnerId() {
        return partnerId;
    }
    
    public void setPartnerId(String partner_id ) {
    	this.partnerId = partner_id;
    }
    
    public long getDuration() {
    	return this.duration;
    }
    
    public void setDuration(long duration) {
    	this.duration = duration;
    	this.expiration = (System.currentTimeMillis() / 1000) + duration;
    }

    public String getAdContent() {
        return adContent;
    }
    
    public void setAdContent(String ad_content) {
    	this.adContent = ad_content;
    }
    
    public String getAdTitle() {
    	return adTitle;
    }
    
    public void setAdTitle(String ad_title) {
    	this.adTitle = ad_title;
    }
    
    public AdStatus getAdStatus() {
    	return adStatus;
    }
    
    public long getExpiration() {
    	return expiration;
    }
}
