package search;

public class Pokemon {
	private int level;
	private int hp;
	private String name;
	private boolean poisoned;
	
	public Pokemon(String name, int level) {
		this.name = name;
		this.level = level;
		this.hp = 100;
		this.poisoned = false;
	}
	
	 public static void main(String[] args) {
		 Pokemon squirtle = new Pokemon("Squirtle",26);
		 Pokemon bulbasaur = new Pokemon("Bulbasaur",26);
		 squirtle.iChooseYou();
		 bulbasaur.iChooseYou();	 
	 }
	
	public void iChooseYou() {
		System.out.print(name + " " + name);
	}
	
	public int getHP() {
		return hp;
	}
	
	private String getName() {
		return name;
	}
	
	public void setHP(int newHP) {
		hp = newHP;
	}
	
	public void setPoisoned(boolean b) {
		poisoned = b;
	}
	
	public void lapse() {
		if(poisoned) {
			setHP(hp-15);
		}
	}
	
	public void attack(Pokemon target, Attack attack){
		if(Math.random() < .9){
			attack.attack(target);
			System.out.println("The attack hit");
		}
		else{
			System.out.println("The attack missed");
		}
	}
	
	public void levelUp(Effect e) {
		level++;
		e.happen();
	}
}