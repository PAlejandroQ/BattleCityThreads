package pc2_BattleCity.serverTest2;

import pc2_BattleCity.Constants;

import java.util.ArrayList;
import java.util.HashMap;



public class ManagementArmament {

    int idAvailable = 0;
    /*
     Lista de objetos que estan suscritos al juego
     Tanques, valas, bockes de edificicios
     Cada objeto:
      {
        idObject:Identificador,
        idOwner: -1,0,1,2,3,4,... [-1 es restringido para objetos que no peretencen a dandie],
        position:(x,y),
        more: moderValue
      }
     */
    private ArrayList<HashMap<String, String>> objects = new ArrayList<HashMap<String, String>>();

    public HashMap<String, String> addObject(HashMap<String, String> object) {
        object.put(Constants.ID_OBJECT_LABEL, Integer.toString(idAvailable));
        objects.add(object);
        idAvailable++;
        return objects.get(idAvailable - 1);
    }

    //Update data objet
    public HashMap<String, String> setObject(HashMap<String, String> object) {
        int index = searchObject(object);
        objects.set(index, object);
        return objects.get(index);
    }

    public void deleteObject(HashMap<String, String> object) {
        int index = searchObject(object);
        objects.remove(index);
    }

    private int searchObject(HashMap<String, String> object) {
        int index = 0;
        for (HashMap<String, String> obj : objects) {
            if (obj.get(Constants.ID_OBJECT_LABEL) == object.get(Constants.ID_OBJECT_LABEL)) break;
            index ++;
        }
        return index;
    }


    /**
     * Creador de objetos armamento
     */

//    private HashMap<String, String> createObject(){
//        HashMap<String, String> newObj = new HashMap<String,String>();
//        newObj.put(Constants.ID_OWNER_LABEL,idOwner);
//        newObj.put(Constants.TYPE_BALA_LABEL,type);
//        newObj.put(Constants.X_POSITION_LABEL,xPosition);
//        newObj.put(Constants.Y_POSITION_LABEL,yPosition);
//        newObj.put(Constants.DIRECTION,)
//    }


//    private HashMap<String,String> unpackJsonOneObjet(String json){
//            return
//    }
//    private String packupToJson(ArrayList<HashMap>)
}
