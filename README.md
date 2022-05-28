# Friends-organization-class
Class to organize friends in a graph in order to find the shortest chain through friends, find cliques of friends in certain universities, and find connector friends(points of articulation) in the graph of friends.

This program implements algorithms on graphs of friends. Edges in this graph represent friendships between two people. An array of 'Person' objects is used to store the names and universities of all the people in the graph, or a null university if a person does not go to university. Each 'Person' object then points to the first node of a linked list of 'Friend' objects representing the friends of that person, each of which has an instance variable 'fnum' that represents that person's index in the array.

The file 'Graph.java' has three classes. The 'Friend' class stores an integer representing a given friend's position in the graph array and a pointer to the next friend in the adjacency linked list. The 'Person' class stores the name of a student, a boolean representing if that person goes to college, the school of the person, and a pointer to a 'Friend' object, which would be a linked list of the friends of the person. Finally, the 'Graph' class stores an array of 'Person' objects representing all of the people in the graph and a HashMap that stores the association of people (name, num). This is used to know what student name is represented by what index in the 'members' array.

Three public methods are in the friends class. 'shortestChain' returns an ArrayList of the shortest chain of friendships between two given people given the parameters of a graph and two people, and uses the private helper method 'makeLowerCase' in order to make sure the method is not case sensitive. 'cliques' returns an arraylist of String arraylists that represents all of the 'cliques' of students given a graph and a school name. A 'clique' is a group of people that are connected by friendships in a given school. For example, if Jane is friends with Roger who is friends with Chris and they are all in Princeton, they would all be in one clique. However, if Jane is friends with Roger who is friends with Chris but only Jane and Chris were in Princeton, then Jane and Chris would be in two separate one-person cliques. This method uses the
