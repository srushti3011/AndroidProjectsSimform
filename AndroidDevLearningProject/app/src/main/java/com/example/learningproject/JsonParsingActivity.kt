package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityJsonParsingBinding
import com.google.gson.Gson
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONObject

class JsonParsingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJsonParsingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityJsonParsingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.userList.text = "Using Native Json Library (org.json)\n"
        var users: MutableList<UserJsonParsingSample> = mutableListOf()
        val jsonData = "{ \"users\" :[ {\"name\":\"Ace\",\"designation\":\"Engineer\"," +
                "\"location\":\"New York\"}, {\"name\":\"Tom\",\"designation\":\"Director" +
                "\",\"location\":\"Chicago\"},{\"name\":\"Tim\",\"designation\":\"Charted " +
                "Accountant\",\"location\":\"Sunnyvale\"}] }"
        val obj = JSONObject(jsonData)
        val userArray = obj.getJSONArray("users")
        for (i in 0 until  userArray.length()) {
            val userAtIndex = userArray.getJSONObject(i)
            val user = UserJsonParsingSample(
                name = userAtIndex.getString("name"),
                designation = userAtIndex.getString("designation"),
                location = userAtIndex.getString("location")
            )
            users.add(user)
            val textToSet = "${binding.userList.text} " +
                    "${user.name} ${user.designation} ${user.location}\n"
            binding.userList.text = textToSet
        }

        binding.tvUserListUsingGson.text = "Using gson\n"
        val usersUsingGson = Gson().fromJson(jsonData, UserArray::class.java)
        for (i in usersUsingGson.users) {
            val textToSet = "${binding.tvUserListUsingGson.text} " +
                    "${i.name} ${i.designation} ${i.location}\n"
            binding.tvUserListUsingGson.text = textToSet
        }

        val jsonString = """{"name":"Alice", "age": 30}"""
        val user = Json.decodeFromString<Human>(jsonString)
        val textToSet = "Using kotlinx serialization\n${user}"
        binding.tvHumanDeserialisingUsingKotlinxSer.text = textToSet

        val mapExample = mapOf(
            "One" to 1,
            "Two" to 2
        )
        val jsonFromMap = Json.encodeToString(mapExample)
        binding.tvKotlinSerMapToJson.text = jsonFromMap
    }
}

data class UserJsonParsingSample(
    val name: String,
    val designation: String,
    val location: String
)
data class UserArray(
    val users: List<UserJsonParsingSample>
)
@Serializable
data class Human(val name: String, val age: Int)