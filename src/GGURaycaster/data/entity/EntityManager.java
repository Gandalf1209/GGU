package GGURaycaster.data.entity;


import GGURaycaster.render.Raycaster;

import java.util.ArrayList;

public class EntityManager {

    ArrayList<Entity> entity;
    ArrayList<Entity> add;
    ArrayList<Entity> remove;

    public EntityManager(){
        entity = new ArrayList<Entity>();
        add = new ArrayList<Entity>();
        remove = new ArrayList<Entity>();
    }

    public void add(Entity entity){
        add.add(entity);
    }
    public void remove(Entity entity){
        remove.add(entity);
    }

    public void update(){
        if(add.size() > 0){
            for(Entity ent : add){
                entity.add(ent);
            }
            add.clear();
        }

        if(remove.size() > 0){
            for(Entity ent : remove){
                entity.remove(ent);
            }
            remove.clear();
        }

        for(Entity ent : entity){
            ent.update();
        }
    }
    public void render(Raycaster raycaster){

        for(Entity ent : entity){
            ent.render(raycaster);
        }

    }


}
