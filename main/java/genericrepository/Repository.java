package genericrepository;

import exception.InvalidUserIdException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository<T> {
    private final Map<Integer, T> genericRepo = new HashMap<>();

    public void add(int Id, T entity) {
        genericRepo.put(Id, entity);
    }

    public void update(int Id, T newEntity) throws InvalidUserIdException {
        if (!genericRepo.containsKey(Id)) {
            throw new InvalidUserIdException("this Id does not exist in repository");
        } else {
            genericRepo.replace(Id, genericRepo.get(Id), newEntity);
        }
    }

    public T find(int Id) throws InvalidUserIdException {
        if (!genericRepo.containsKey(Id)) {
            throw new InvalidUserIdException("this id does not exist in the repo");
        } else {
            return genericRepo.get(Id);
        }
    }

    public void remove(int Id) {
        genericRepo.remove(Id);
    }

    public Map<Integer,T> getAll() {

        return genericRepo;
    }
}