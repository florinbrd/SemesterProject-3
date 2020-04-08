import com.google.gson.Gson;
import javaModel.Group;
import javaModel.ProductBacklog;

public class Ran {

    public static void main(String[] args) {
        Gson gson = new Gson();
        String json = "{\n" +
                "    \"sprintList\": {\n" +
                "        \"duration\": 0,\n" +
                "        \"sprintsList\": null,\n" +
                "        \"numberOfSprints\": 0,\n" +
                "        \"groupID\": 0,\n" +
                "        \"currentlyActiveSprint\": 0,\n" +
                "        \"validated\": false\n" +
                "    },\n" +
                "    \"productBacklog\": {\n" +
                "        \"groupId\": 0,\n" +
                "        \"userStories\": []\n" +
                "    },\n" +
                "    \"groupMembers\": [\n" +
                "        {\n" +
                "            \"user\": {\n" +
                "                \"id\": 0,\n" +
                "                \"validated\": false,\n" +
                "                \"username\": \"dave\",\n" +
                "                \"password\": null\n" +
                "            },\n" +
                "            \"role\": \"LOL\",\n" +
                "            \"groupID\": 1,\n" +
                "            \"validated\": false,\n" +
                "            \"username\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"user\": {\n" +
                "                \"id\": 0,\n" +
                "                \"validated\": false,\n" +
                "                \"username\": \"jamie\",\n" +
                "                \"password\": null\n" +
                "            },\n" +
                "            \"role\": \"scruman\",\n" +
                "            \"groupID\": 1,\n" +
                "            \"validated\": false,\n" +
                "            \"username\": null\n" +
                "        }\n" +
                "    ],\n" +
                "    \"groupId\": 1,\n" +
                "    \"validated\": false\n" +
                "}";
        Group productBacklog = gson.fromJson(json, Group.class);
        System.out.println(productBacklog.groupMembers.get(0).getRole());
    }
}
