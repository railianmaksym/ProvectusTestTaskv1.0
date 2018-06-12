package com.railian.maksym.provectustesttask.utilites

import com.railian.maksym.provectustesttask.models.Location
import com.railian.maksym.provectustesttask.models.Login
import com.railian.maksym.provectustesttask.models.Name
import com.railian.maksym.provectustesttask.models.Picture
import com.railian.maksym.provectustesttask.models.Result
import com.railian.maksym.provectustesttask.repository.DatabaseModel

import java.util.ArrayList


// Mapper to fixing incompatibility gson and DBFlow annotations
class JsonMapper {

    fun convertFromNetworkModel(results: List<Result>): List<DatabaseModel> {

        val databaseModels = ArrayList<DatabaseModel>()

        // Convert list of Result to list DatabaseModel for insert in database
        for (i in results.indices) {
            val databaseModel = DatabaseModel()
            databaseModel.id = i + 1
            databaseModel.title = formateCorrectNames(results[i].name?.title)
            databaseModel.first = formateCorrectNames(results[i].name?.first)
            databaseModel.last = formateCorrectNames(results[i].name?.last)
            databaseModel.street = formateCorrectNames(results[i].location?.street)
            databaseModel.city = formateCorrectNames(results[i].location?.city)
            databaseModel.state = formateCorrectNames(results[i].location?.state)
            databaseModel.postcode = results[i].location!!.postcode
            databaseModel.username = results[i].login!!.username
            databaseModel.thumbnail = results[i].picture!!.thumbnail
            databaseModel.medium = results[i].picture!!.medium
            databaseModel.large = results[i].picture!!.large
            databaseModel.gender = results[i].gender
            databaseModel.email = results[i].email
            databaseModel.dob = results[i].dob
            databaseModel.registered = results[i].registered
            databaseModel.phone = results[i].phone
            databaseModel.cell = results[i].cell
            databaseModel.nat = results[i].nat
            databaseModels.add(databaseModel)
        }
        return databaseModels
    }

    fun convertToNetworkModel(databaseModels: List<DatabaseModel>): List<Result> {

        val results = ArrayList<Result>()

        // Convert list of DatabaseModel to list of Result for after select from database
        for (i in databaseModels.indices) {
            val result = Result()
            result.name = Name(formateCorrectNames(databaseModels[i].title),
                    formateCorrectNames(databaseModels[i].first),
                    formateCorrectNames(databaseModels[i].last))
            result.location = Location(formateCorrectNames(databaseModels[i].street),
                    formateCorrectNames(databaseModels[i].city),
                    formateCorrectNames(databaseModels[i].state),
                    formateCorrectNames(databaseModels[i].postcode))
            result.login = Login(databaseModels[i].username)
            result.picture = Picture(databaseModels[i].thumbnail,
                    databaseModels[i].medium,
                    databaseModels[i].large)
            result.gender = databaseModels[i].gender
            result.email = databaseModels[i].email
            result.dob = databaseModels[i].dob
            result.registered = databaseModels[i].registered
            result.phone = databaseModels[i].phone
            result.cell = databaseModels[i].cell
            result.nat = databaseModels[i].nat
            results.add(result)
        }
        return results
    }

    // For the title to begin with a capital letter
    fun formateCorrectNames(name: String?): String {

        val namechar = name!!.toCharArray()
        for (i in namechar.indices) {
            if (i == 0)
                namechar[i] = namechar[i].toString().toUpperCase().get(0)
            else if (namechar[i - 1].toString() == " ") {

                namechar[i] = namechar[i].toString().toUpperCase().get(0)
            }
        }

        return String(namechar)


    }
}
