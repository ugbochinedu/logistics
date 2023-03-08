package data.repositories;

import data.model.Package;

import java.util.ArrayList;
import java.util.List;

public class PackageRepositoryImpl implements PackageRepository{

    private int count;

    private List<Package> packages = new ArrayList<>();
    @Override
    public Package save(Package aPackage) {
        boolean isSaved = aPackage.getId() != 0;
        if (isSaved) update(aPackage); else saveNewPackage(aPackage);

        return aPackage;
    }
    private void update(Package aPackage) {
        Package savedPackage = findById(aPackage.getId());
        int indexOfSavedPackage = packages.indexOf(savedPackage);
        packages.set(indexOfSavedPackage,aPackage);

//        Package savedPackage = findById(aPackage.getId());
//        packages.remove(savedPackage);
//        packages.add(aPackage);
    }

    private void saveNewPackage(Package aPackage){
        packages.add(aPackage);
        aPackage.setId(generatePackageId());
        count++;
    }

    @Override
    public Package findById(int id) {
        for (Package aPackage: packages) if(aPackage.getId() == id) return aPackage;
        return null;
    }


    private int generatePackageId() {
        return count+1;
    }

    @Override
    public void delete(Package aPackage) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Package> findAll() {
        return null;
    }


    @Override
    public long count() {
        return count;
    }
}
