package dev.game;

import dev.game.gfx.Assets;

public class Plant extends Entity {
	private int charge=0;
	private int eggCost;
	public Plant(int posX, int posY, int velX, int velY) {
		super(posX, posY, velX, velY, Assets.plant);
		/* Will vary for different plants in future */
		this.eggCost = 100;
	}

	public Plant(Plant plant, int posX, int posY){
		super(posX, posY, plant.getVelX(), plant.getVelY(), plant.getSprite());
	}

	public void shoot() {}
	@Override
	public void update() {
		super.update();

		//Basic collision checking
		for(Entity entity: Game.getInstance().getRoom().getEntities()){
			if (entity instanceof Zombie){
				//Meme-worthy collision checking, someone plz write something good
				if (Math.abs(entity.getPosY()-getPosY())<20){
					if (charge<100){
						charge++;
					}
					else
					{
						charge=0;
						Game.getInstance().addEntity(new Bullet(getPosX(),getPosY()));
					}

					if(Math.abs(entity.getPosX()-getPosX())<20){
						Game.getInstance().removeEntity(this);
					}

				}
			}
		}
	}

	public int getEggCost() {
		return this.eggCost;
	}
}
