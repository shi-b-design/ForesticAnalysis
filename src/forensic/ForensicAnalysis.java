package forensic;

import java.util.Arrays;



/**
 * This class represents a forensic analysis system that manages DNA data using
 * BSTs.
 * Contains methods to create, read, update, delete, and flag profiles.
 * 
 * @author Kal Pandit
 */
public class ForensicAnalysis {

    private TreeNode treeRoot;            // BST's root
    private String firstUnknownSequence;
    private String secondUnknownSequence;

    public ForensicAnalysis () {
        treeRoot = null;
        firstUnknownSequence = null;
        secondUnknownSequence = null;
    }

    /**
     * Builds a simplified forensic analysis database as a BST and populates unknown sequences.
     * The input file is formatted as follows:
     * 1. one line containing the number of people in the database, say p
     * 2. one line containing first unknown sequence
     * 3. one line containing second unknown sequence
     * 2. for each person (p), this method:
     * - reads the person's name
     * - calls buildSingleProfile to return a single profile.
     * - calls insertPerson on the profile built to insert into BST.
     *      Use the BST insertion algorithm from class to insert.
     * 
     * DO NOT EDIT this method, IMPLEMENT buildSingleProfile and insertPerson.
     * 
     * @param filename the name of the file to read from
     */
    public void buildTree(String filename) {
        // DO NOT EDIT THIS CODE
        StdIn.setFile(filename); // DO NOT remove this line

        // Reads unknown sequences
        String sequence1 = StdIn.readLine();
        firstUnknownSequence = sequence1;
        String sequence2 = StdIn.readLine();
        secondUnknownSequence = sequence2;
        
        int numberOfPeople = Integer.parseInt(StdIn.readLine()); 

        for (int i = 0; i < numberOfPeople; i++) {
            // Reads name, count of STRs
            String fname = StdIn.readString();
            String lname = StdIn.readString();
            String fullName = lname + ", " + fname;
            // Calls buildSingleProfile to create
            Profile profileToAdd = createSingleProfile();
            // Calls insertPerson on that profile: inserts a key-value pair (name, profile)
            insertPerson(fullName, profileToAdd);
        }
    }

    /** 
     * Reads ONE profile from input file and returns a new Profile.
     * Do not add a StdIn.setFile statement, that is done for you in buildTree.
    */
    public Profile createSingleProfile() {

        Integer  s = StdIn.readInt();
        STR STR_array [] =  new STR[s];
       

        for(int i = 0; i < s ; i++){
            String STR_name = StdIn.readString();
            Integer occurance = StdIn.readInt();
            STR Str = new STR(STR_name, occurance);
            
        
        
            int Space = 0;
            for(int j = 0; j < STR_array.length; j++){ // iterate through the number of the size of array
                if(STR_array[j]==null){
                    Space = j;
                    break;
                    
                
            }

            
        }
        STR_array[Space] = Str;

    }
    
    

        Profile proFile = new Profile();
        proFile.setStrs(STR_array);

    
        

        // WRITE YOUR CODE HERE
        
        return proFile; // update this line
    }

    /**
     * Inserts a node with a new (key, value) pair into
     * the binary search tree rooted at treeRoot.
     * 
     * Names are the keys, Profiles are the values.
     * USE the compareTo method on keys.
     * 
     * @param newProfile the profile to be inserted
     */
    public void insertPerson(String name, Profile newProfile) {

        
        TreeNode treeNode = new TreeNode();
        treeNode.setName(name);
        treeNode.setProfile(newProfile);
        TreeNode currentTree = treeRoot;

        if(treeRoot == null){
            treeRoot = treeNode;
            return;
        }
        
        else{
             //compare the name of the root and the node you want to add

        
            while(true){
                String pName = treeNode.getName();
                int compare = pName.compareTo(currentTree.getName());
                

                if (compare < 0) {
                    if(currentTree.getLeft()== null){
                        currentTree.setLeft(treeNode);
                        return;
                    
                    }
                    
                
                    else{currentTree = currentTree.getLeft(); // Try to find ways to make node with sorted order. 
                        
                    }
                    
                    
                }
               

                else if (compare > 0){
                    if (currentTree.getRight() == null) {
                         currentTree.setRight(treeNode);
                         return;
                        
                    }
                    
                    
                    else{currentTree = currentTree.getRight();
                        
                    }
                    
                    }
                
                    
                

                else {
                    currentTree.setProfile(newProfile);
                    return;
                }
                }    

        }
    }

   
    

        // WRITE YOUR CODE HERE
    

    /**
     * Finds the number of profiles in the BST whose interest status matches
     * isOfInterest.
     *
     * @param isOfInterest the search mode: whether we are searching for unmarked or
     *                     marked profiles. true if yes, false otherwise
     * @return the number of profiles according to the search mode marked
     */

     
     
     private int numberChecker(TreeNode treeNode, boolean isOfInterest, int count){

        TreeNode currentNode  = treeNode;

        if(currentNode == null){
            return count;
        }

        else{
            count = numberChecker(treeNode.getLeft(), isOfInterest, count);
            //Profile profile = currentNode.getProfile().;
            
            
            if(currentNode.getProfile().getMarkedStatus() == isOfInterest){
                count++;
            }
            
            
            count = numberChecker(treeNode.getRight(), isOfInterest, count);

            return count;

            
        }






    }
     
    public int getMatchingProfileCount(boolean isOfInterest) {

        int count = 0;

        count = numberChecker(treeRoot,isOfInterest,count);
        
        // WRITE YOUR CODE HERE
        return count; // update this line
    }

    /**
     * Helper method that counts the # of STR occurrences in a sequence.
     * Provided method - DO NOT UPDATE.
     * 
     * @param sequence the sequence to search
     * @param STR      the STR to count occurrences of
     * @return the number of times STR appears in sequence
     */
    private int numberOfOccurrences(String sequence, String STR) {
        
        // DO NOT EDIT THIS CODE
        
        int repeats = 0;
        // STRs can't be greater than a sequence
        if (STR.length() > sequence.length())
            return 0;
        
            // indexOf returns the first index of STR in sequence, -1 if not found
        int lastOccurrence = sequence.indexOf(STR);
        
        while (lastOccurrence != -1) {
            repeats++;
            // Move start index beyond the last found occurrence
            lastOccurrence = sequence.indexOf(STR, lastOccurrence + STR.length());
        }
        return repeats;
    }

    private void traverse(TreeNode treeNode){
        TreeNode currentNode = treeNode; // you go to left 

        if(currentNode == null){
            return;
        }
        
        
        else{ 
            
            STR [] strCurrent = currentNode.getProfile().getStrs();

            int Count = 0;
            for(int i = 0; i < strCurrent.length; i++){
                String string = strCurrent[i].getStrString();
                Integer occurance = strCurrent[i].getOccurrences();
                Integer numberOccure1= numberOfOccurrences(firstUnknownSequence, string);
                Integer numberOccure2 = numberOfOccurrences(secondUnknownSequence, string);
                
                
                

                if(occurance == (numberOccure1 + numberOccure2)){
                    Count++;
                       
                }

                
                double j = Math.ceil((strCurrent.length)/2.0); // number "2,0" has to be double 
                int num = (int)j;

                

                if (Count >= num) {
                    currentNode.getProfile().setInterestStatus(true);
                    
                }

            }

            
            traverse(currentNode.getLeft());
            traverse(currentNode.getRight());
            
        
    }
}

    /**
     * Traverses the BST at treeRoot to mark profiles if:
     * - For each STR in profile STRs: at least half of STR occurrences match (round
     * UP)
     * - If occurrences THROUGHOUT DNA (first + second sequence combined) matches
     * occurrences, add a match
     */
    public void flagProfilesOfInterest() {
        
        

        

        traverse(treeRoot);


        
            
            
              // you make a helper method.
              // you have to count 
        

    
        

        // WRITE YOUR CODE HERE
    }

    

    /**
     * Uses a level-order traversal to populate an array of unmarked Strings representing unmarked people's names.
     * - USE the getMatchingProfileCount method to get the resulting array length.
     * - USE the provided Queue class to investigate a node and enqueue its
     * neighbors.
     * 
     * @return the array of unmarked people
     */

    
    
    public String[] getUnmarkedPeople() {
        
        
        int numberArray = getMatchingProfileCount(false);
        String [] arrayUnmark = new String[numberArray];

        
        if (treeRoot == null) {
            return new String[0];
            
        }

       Queue<TreeNode> queue = new Queue<>();
       queue.enqueue(treeRoot);
       int pos =0;

        while(!queue.isEmpty()){
            TreeNode currentNode = queue.dequeue();
            
        
            if(currentNode.getProfile().getMarkedStatus() == false){
                
                
                arrayUnmark[pos++] =currentNode.getName();
                
                


            }

            if(currentNode.getLeft() != null){
                queue.enqueue(currentNode.getLeft());
            
            }
            
            if(currentNode.getRight() != null){
            queue.enqueue(currentNode.getRight());
            
            }
           
            
    }
    
    return arrayUnmark;


    // WRITE YOUR CODE HERE
     // update this line
    }

    /**
     * Removes a SINGLE node from the BST rooted at treeRoot, given a full name (Last, First)
     * This is similar to the BST delete we have seen in class.
     * 
     * If a profile containing fullName doesn't exist, do nothing.
     * You may assume that all names are distinct.
     * 
     * @param fullName the full name of the person to delete
     */
    public void removePerson(String fullName) {
        // WRITE YOUR CODE HERE

        TreeNode currentNode = treeRoot;
        TreeNode parNode = null; // pointer for following parent of currentNode
        

       




        if(treeRoot == null){
            return;
        }

        else{
            
            // Try to find a node you want to delete
            //while(fullName != currentNode.getName()){
                
                do{
                int compare = fullName.compareTo(currentNode.getName()); 
                if(compare == 0){
                    
                    break;
   
                
                }

                if(compare < 0){
                    parNode = currentNode;
                    currentNode = currentNode.getLeft();
                    

                }

                if(compare > 0){
                    parNode = currentNode;
                    currentNode= currentNode.getRight();
                    
                }

            }while(fullName != currentNode.getName());

            //}
            // you want to compare with parent node. if the node is greater than parent, you want to set null on the right of parent
            // This is a condition that a node you want to delete has no child. 
            
            
            
            //int compareParChi = parNode.getName().compareTo(currentNode.getName()); 
        
            if(currentNode.getLeft()== null && currentNode.getRight() == null){
                if(parNode != null){
                    if(parNode.getLeft() == currentNode){
                        parNode.setLeft(null);
                    }

                    else{
                        parNode.setRight(null);
                    }
                }

                else{
                    treeRoot = null;
                }
                    

                }
            // This is a condition that you want to delete a node with one child. 
            
            else if(currentNode.getLeft() == null || currentNode.getRight() == null){

                TreeNode childNode = (currentNode.getLeft() != null)? currentNode.getLeft(): currentNode.getRight();
                if(parNode != null){
                    if(parNode.getLeft() == currentNode){
                    parNode.setLeft(childNode);    
                }

                else{
                    parNode.setRight(childNode);
                }
                }

                else{
                    treeRoot = childNode;
                    
                    }
                }

            // This is a condititon that a node you want to delete has two children
            else{
                if(parNode != null){
                    TreeNode follow = currentNode.getRight(); // a pointer to find a smallest number in tree
                    TreeNode currentLeftChild = currentNode.getLeft();
                    TreeNode currentRightChild = currentNode.getRight();

                    TreeNode followPar = follow;
                    while(follow.getLeft() != null){
                        followPar = follow;
                        follow = follow.getLeft();
                         
                        
                    }
                    TreeNode followChild = follow.getRight(); // the right child of the min node 
                    followPar.setLeft(followChild);
                    
                    
                    int compare = parNode.getName().compareTo(currentNode.getName()); // you have to find a place to set the node. How can I find a place to replace a node to min node in tree.?
                    if(compare > 0){
                        parNode.setLeft(follow);
                        
                    }
                    if(compare < 0){
                        parNode.setRight(follow);
                    }

                    follow.setLeft(currentLeftChild);
                    if(follow == currentRightChild){
                    
                    follow.setRight(null);
                    }

                    else{
                        follow.setRight(currentRightChild);
                    }
                }

                else{
                    TreeNode follow = currentNode.getRight();
                    TreeNode currentRightChild = currentNode.getRight();
                    TreeNode currentLeftChild = currentNode.getLeft();
                    TreeNode followPar = follow;

                    while(follow.getLeft() != null){
                        followPar = follow;
                        follow = follow.getLeft();
                    
  
                }

                currentNode.setName(follow.getName());
                currentNode.setProfile(follow.getProfile());

                currentNode.setLeft(currentLeftChild);
                currentNode.setRight(currentRightChild);
                
                followPar.setLeft(follow.getRight());
            }
        }
    }
}
        
    

    /**
     * Clean up the tree by using previously written methods to remove unmarked
     * profiles.
     * Requires the use of getUnmarkedPeople and removePerson.
     */
    public void cleanupTree() {
        // WRITE YOUR CODE HERE

    String [] peopleUnmarked = getUnmarkedPeople();
    for(String fullName : peopleUnmarked){
        removePerson(fullName);
    }
    }
    

    

    /**
     * Gets the root of the binary search tree.
     *
     * @return The root of the binary search tree.
     */
    public TreeNode getTreeRoot() {
        return treeRoot;
    }

    /**
     * Sets the root of the binary search tree.
     *
     * @param newRoot The new root of the binary search tree.
     */
    public void setTreeRoot(TreeNode newRoot) {
        treeRoot = newRoot;
    }

    /**
     * Gets the first unknown sequence.
     * 
     * @return the first unknown sequence.
     */
    public String getFirstUnknownSequence() {
        return firstUnknownSequence;
    }

    /**
     * Sets the first unknown sequence.
     * 
     * @param newFirst the value to set.
     */
    public void setFirstUnknownSequence(String newFirst) {
        firstUnknownSequence = newFirst;
    }

    /**
     * Gets the second unknown sequence.
     * 
     * @return the second unknown sequence.
     */
    public String getSecondUnknownSequence() {
        return secondUnknownSequence;
    }

    /**
     * Sets the second unknown sequence.
     * 
     * @param newSecond the value to set.
     */
    public void setSecondUnknownSequence(String newSecond) {
        secondUnknownSequence = newSecond;
    }

}
