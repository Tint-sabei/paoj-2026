package com.pao.project.etapa1.service;

import com.pao.project.etapa1.model.Owner;
import java.util.*;

public class OwnerService {
    private final Map<String, Owner> owners = new HashMap<>();

    private OwnerService(){}

    private static class Holder {
        private static final OwnerService instance = new OwnerService();
    }

    public static OwnerService getInstance(){return Holder.instance;}

    // add
    public void addOwner(Owner owner){
        if (owner != null){
            owners.put(owner.getId(), owner);
        }
    }

    // find by id
    public Owner findById(String id){
        return owners.get(id);
    }

    // list all
    public List<Owner> getAll(){
        return new ArrayList<>(owners.values());
    }

    // delete
    public void deleteOwner(String id){
        owners.remove(id);
    }
}
