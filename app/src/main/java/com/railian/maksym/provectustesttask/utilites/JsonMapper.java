package com.railian.maksym.provectustesttask.utilites;

import com.railian.maksym.provectustesttask.models.Location;
import com.railian.maksym.provectustesttask.models.Login;
import com.railian.maksym.provectustesttask.models.Name;
import com.railian.maksym.provectustesttask.models.Picture;
import com.railian.maksym.provectustesttask.models.Result;
import com.railian.maksym.provectustesttask.repository.DatabaseModel;

import java.util.ArrayList;
import java.util.List;


// Mapper to fixing incompatibility gson and DBFlow annotations
public class JsonMapper {

    public List<DatabaseModel> convertFromNetworkModel(List<Result> results) {

        List<DatabaseModel> databaseModels = new ArrayList<>();

        // Convert list of Result to list DatabaseModel for insert in database
        for (int i = 0; i < results.size(); i++) {
            DatabaseModel databaseModel = new DatabaseModel();
            databaseModel.setId(i + 1);
            databaseModel.setTitle(formateCorrectNames(results.get(i).getName().getTitle()));
            databaseModel.setFirst(formateCorrectNames(results.get(i).getName().getFirst()));
            databaseModel.setLast(formateCorrectNames(results.get(i).getName().getLast()));
            databaseModel.setStreet(formateCorrectNames(results.get(i).getLocation().getStreet()));
            databaseModel.setCity(formateCorrectNames(results.get(i).getLocation().getCity()));
            databaseModel.setState(formateCorrectNames(results.get(i).getLocation().getState()));
            databaseModel.setPostcode(results.get(i).getLocation().getPostcode());
            databaseModel.setUsername(results.get(i).getLogin().getUsername());
            databaseModel.setThumbnail(results.get(i).getPicture().getThumbnail());
            databaseModel.setMedium(results.get(i).getPicture().getMedium());
            databaseModel.setLarge(results.get(i).getPicture().getLarge());
            databaseModel.setGender(results.get(i).getGender());
            databaseModel.setEmail(results.get(i).getEmail());
            databaseModel.setDob(results.get(i).getDob());
            databaseModel.setRegistered(results.get(i).getRegistered());
            databaseModel.setPhone(results.get(i).getPhone());
            databaseModel.setCell(results.get(i).getCell());
            databaseModel.setNat(results.get(i).getNat());
            databaseModels.add(databaseModel);
        }
        return databaseModels;
    }

    public List<Result> convertToNetworkModel(List<DatabaseModel> databaseModels) {

        List<Result> results = new ArrayList<>();

        // Convert list of DatabaseModel to list of Result for after select from database
        for (int i = 0; i < databaseModels.size(); i++) {
            Result result = new Result();
            result.setName(new Name(formateCorrectNames(databaseModels.get(i).getTitle()),
                    formateCorrectNames(databaseModels.get(i).getFirst()),
                    formateCorrectNames(databaseModels.get(i).getLast())));
            result.setLocation(new Location(formateCorrectNames(databaseModels.get(i).getStreet()),
                    formateCorrectNames(databaseModels.get(i).getCity()),
                    formateCorrectNames(databaseModels.get(i).getState()),
                    formateCorrectNames(databaseModels.get(i).getPostcode())));
            result.setLogin(new Login(databaseModels.get(i).getUsername()));
            result.setPicture(new Picture(databaseModels.get(i).getThumbnail(),
                    databaseModels.get(i).getMedium(),
                    databaseModels.get(i).getLarge()));
            result.setGender(databaseModels.get(i).getGender());
            result.setEmail(databaseModels.get(i).getEmail());
            result.setDob(databaseModels.get(i).getDob());
            result.setRegistered(databaseModels.get(i).getRegistered());
            result.setPhone(databaseModels.get(i).getPhone());
            result.setCell(databaseModels.get(i).getCell());
            result.setNat(databaseModels.get(i).getNat());
            results.add(result);
        }
        return results;
    }

    // For the title to begin with a capital letter
    public String formateCorrectNames(String name) {

        char[] namechar = name.toCharArray();
        for (int i = 0; i < namechar.length; i++) {
            if (i == 0)
                namechar[i] = String.valueOf(namechar[i]).toUpperCase().charAt(0);
            else if (String.valueOf(namechar[i - 1]).equals(" ")) {

                namechar[i] = String.valueOf(namechar[i]).toUpperCase().charAt(0);
            }
        }

        return String.copyValueOf(namechar);


    }
}
