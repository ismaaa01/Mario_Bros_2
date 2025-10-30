package tp1_2.logic;

import tp1_2.logic.Action;
import java.util.*;

public class ActionList {
	private List <Action> actList;
	private int n_left;
	private int n_right;
	private int n_up;
	private boolean down;
	private boolean vacio;

	
	
	public ActionList() {
		actList = new ArrayList<>();
		n_left = 0;
		n_right = 0;
		n_up = 0;
		down = false;
		vacio = true;
	}
	
	public int size() {
		return actList.size();
	}
	
	public void eliminate() {
		n_left = 0;
		n_right = 0;
		n_up = 0;
		down = false;
		vacio = true;
		actList.removeAll(actList);
	}
	
	public void remove_first() {
		actList.remove(0);
		if(actList.size()== 0) {
			vacio = true;
		}
	}
	
	public void add(Action act) {
		actList.add(act);
		if(vacio) vacio = false;
	}
	
	public boolean in_action() {
		return !vacio;
	}
	
	public Action get(int i) {
		return actList.get(i);
	}

	public void simplify() {
		for (int i = 0; i < actList.size(); i++) {
			if((n_right >= 4 || n_left >= 4 )&& n_up >= 4) {
				actList.remove(i);
				i--;
			}else {
				if(actList.get(i) == Action.RIGHT && n_left == 0 && n_right < 4) {
					n_right++;
				}else if(actList.get(i) == Action.LEFT && n_right== 0 && n_left < 4) {
					n_left++;
				}else if(actList.get(i) == Action.UP && !down && n_up < 4) {
					n_up++;
				}else if(actList.get(i) == Action.DOWN && n_up == 0) {
					down = true;
				}else if (actList.get(i) != Action.STOP) {
					actList.remove(i);
					i--;
				}
			}	
		}							
	}
}

