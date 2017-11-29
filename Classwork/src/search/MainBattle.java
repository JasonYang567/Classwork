package search;

public class MainBattle {
	public static void main(String[] args) {
		Pokemon squirtle = new Pokemon("Squirtle",26);
		Pokemon bulbasaur = new Pokemon("Bulbasaur",26);
		squirtle.iChooseYou();
		bulbasaur.iChooseYou();
		System.out.println("Squirtle is preparing to attack with water blast");
		squirtle.attack(bulbasaur, new Attack() {
			public void attack(Pokemon target) {
				int hp = target.getHP();
				target.setHP(hp/2);
			}
		});
		System.out.println("Bulbasaur is preparing to attack with poison.");
		bulbasaur.attack(squirtle, new Attack() {
			public void attack(Pokemon target) {
				target.setPoisoned(true);
			}
		});
		squirtle.lapse();
		bulbasaur.lapse();
		System.out.println("Squirtle is attcking again");
		squirtle.attack(bulbasaur, new Attack(){
			
			public void attack(Pokemon target) {
				int hp = target.getHP();
				target.setHP(hp-100);
			}
		});
		squirtle.levelUp(new Effect() {
			
			public void happen() {
				squirtle.setHP(100);
			}
		});
	}
}
