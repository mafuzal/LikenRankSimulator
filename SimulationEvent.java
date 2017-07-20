import java.util.*;

@SuppressWarnings("serial")
public class SimulationEvent extends EventObject {

	public SimulationEvent(List<User> list) {
		super(list);
		output(list);
		// TODO Auto-generated constructor stub
	}
	public SimulationEvent(){
		super(0);
				
	}
	

	public void output(Object o) {

		System.out.println(o);
	}
}
