
package st.tiy.lpq.model.remote.cdragon.champion;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"Effect1Amount",
		"Effect2Amount",
		"Effect3Amount",
		"Effect4Amount",
		"Effect5Amount",
		"Effect6Amount",
		"Effect7Amount",
		"Effect8Amount",
		"Effect9Amount",
		"Effect10Amount"
})
public class EffectAmounts {

	@JsonProperty("Effect1Amount")
	private List<Double> effect1Amount;
	@JsonProperty("Effect2Amount")
	private List<Double> effect2Amount;
	@JsonProperty("Effect3Amount")
	private List<Double> effect3Amount;
	@JsonProperty("Effect4Amount")
	private List<Double> effect4Amount;
	@JsonProperty("Effect5Amount")
	private List<Double> effect5Amount;
	@JsonProperty("Effect6Amount")
	private List<Double> effect6Amount;
	@JsonProperty("Effect7Amount")
	private List<Double> effect7Amount;
	@JsonProperty("Effect8Amount")
	private List<Double> effect8Amount;
	@JsonProperty("Effect9Amount")
	private List<Double> effect9Amount;
	@JsonProperty("Effect10Amount")
	private List<Double> effect10Amount;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("Effect1Amount")
	public List<Double> getEffect1Amount() {
		return effect1Amount;
	}

	@JsonProperty("Effect1Amount")
	public void setEffect1Amount(List<Double> effect1Amount) {
		this.effect1Amount = effect1Amount;
	}

	@JsonProperty("Effect2Amount")
	public List<Double> getEffect2Amount() {
		return effect2Amount;
	}

	@JsonProperty("Effect2Amount")
	public void setEffect2Amount(List<Double> effect2Amount) {
		this.effect2Amount = effect2Amount;
	}

	@JsonProperty("Effect3Amount")
	public List<Double> getEffect3Amount() {
		return effect3Amount;
	}

	@JsonProperty("Effect3Amount")
	public void setEffect3Amount(List<Double> effect3Amount) {
		this.effect3Amount = effect3Amount;
	}

	@JsonProperty("Effect4Amount")
	public List<Double> getEffect4Amount() {
		return effect4Amount;
	}

	@JsonProperty("Effect4Amount")
	public void setEffect4Amount(List<Double> effect4Amount) {
		this.effect4Amount = effect4Amount;
	}

	@JsonProperty("Effect5Amount")
	public List<Double> getEffect5Amount() {
		return effect5Amount;
	}

	@JsonProperty("Effect5Amount")
	public void setEffect5Amount(List<Double> effect5Amount) {
		this.effect5Amount = effect5Amount;
	}

	@JsonProperty("Effect6Amount")
	public List<Double> getEffect6Amount() {
		return effect6Amount;
	}

	@JsonProperty("Effect6Amount")
	public void setEffect6Amount(List<Double> effect6Amount) {
		this.effect6Amount = effect6Amount;
	}

	@JsonProperty("Effect7Amount")
	public List<Double> getEffect7Amount() {
		return effect7Amount;
	}

	@JsonProperty("Effect7Amount")
	public void setEffect7Amount(List<Double> effect7Amount) {
		this.effect7Amount = effect7Amount;
	}

	@JsonProperty("Effect8Amount")
	public List<Double> getEffect8Amount() {
		return effect8Amount;
	}

	@JsonProperty("Effect8Amount")
	public void setEffect8Amount(List<Double> effect8Amount) {
		this.effect8Amount = effect8Amount;
	}

	@JsonProperty("Effect9Amount")
	public List<Double> getEffect9Amount() {
		return effect9Amount;
	}

	@JsonProperty("Effect9Amount")
	public void setEffect9Amount(List<Double> effect9Amount) {
		this.effect9Amount = effect9Amount;
	}

	@JsonProperty("Effect10Amount")
	public List<Double> getEffect10Amount() {
		return effect10Amount;
	}

	@JsonProperty("Effect10Amount")
	public void setEffect10Amount(List<Double> effect10Amount) {
		this.effect10Amount = effect10Amount;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
