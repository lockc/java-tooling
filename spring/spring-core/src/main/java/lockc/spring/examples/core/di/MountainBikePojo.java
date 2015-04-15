package lockc.spring.examples.core.di;

public class MountainBikePojo {

	private FrontBrakes frontBrakes;
	private RearBrakes rearBrakes;
	private FrontSuspension frontSuspension;
	private Gears gears;
	
	public MountainBikePojo() {}
	
	public MountainBikePojo(FrontBrakes frontBrakes, RearBrakes rearBrakes,
			FrontSuspension frontSuspension, Gears gears) {
		super();
		this.frontBrakes = frontBrakes;
		this.rearBrakes = rearBrakes;
		this.frontSuspension = frontSuspension;
		this.gears = gears;
	}
	
	public FrontBrakes getFrontBrakes() {
		return frontBrakes;
	}
	public void setFrontBrakes(FrontBrakes frontBrakes) {
		this.frontBrakes = frontBrakes;
	}
	public RearBrakes getRearBrakes() {
		return rearBrakes;
	}
	public void setRearBrakes(RearBrakes rearBrakes) {
		this.rearBrakes = rearBrakes;
	}
	public FrontSuspension getFrontSuspension() {
		return frontSuspension;
	}
	public void setFrontSuspension(FrontSuspension frontSuspension) {
		this.frontSuspension = frontSuspension;
	}
	public Gears getGears() {
		return gears;
	}
	public void setGears(Gears gears) {
		this.gears = gears;
	}
}
