
public class Word {

	private static Tile[] myarrTiles = new Tile[15];
	
	public Word(){
		for(int i = 0; i < 15; i++){
			myarrTiles[i] = new Tile();
		}
	}
	
	public Word(Tile[] arrTiles){
		myarrTiles = arrTiles;
	}
	
	
	public void setTile(Tile tile, int num){
		myarrTiles[num] = tile;
	}
	
	
	public Tile getTile(int num){
		return myarrTiles[num];
	}
	
	public String toString(){
		String word = "";
		for(int i = 0; i < 15; i++){
			if(myarrTiles[i].getLetter() != ""){
				word += myarrTiles[i].getLetter();
			}else{
				break;
			}
		}
		return word;
	}
	
	public int findScore(){
		int score = 0;
		int mult = 0;
		
		for(int i = 0; i < 15; i++){
			if(myarrTiles[i].getLetter() != ""){
				if(myarrTiles[i].getMult() == 2){
					score += myarrTiles[i].getValue() * 2;
				}else if(myarrTiles[i].getMult() == 3){
					score += myarrTiles[i].getValue() * 3;
				}else{
					score += myarrTiles[i].getValue();
				}
				if(myarrTiles[i].getMult() == 1){
					mult = 3;
				}else if(myarrTiles[i].getMult() == 4){
					mult =2;
				}
			}else{
				break;
			}
		}
		
		if(mult != 0){
			score *= mult;
		}
		return score;
	}
	
}
