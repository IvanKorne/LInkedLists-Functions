// LinkedList Assignment
// Ivan Korneychuk
// Making Linked List from scratch.
class LLAssign{
  public static void main(String[] args){
    LList nums = new LList();
    nums.push(2);
    nums.push(4);
    nums.push(4);
    nums.push(4);
    nums.push(6);
    nums.push(6);
    LList clone = nums.clone();
    System.out.println(nums);
    System.out.println(clone);
  }
}

class LList{
  LNode head;
  LNode tail;
  
  public LList(){
    //Initially the head and tail of the linked list is null.
    head = null;
    tail = null;
  }
  
  //Adds element to top of stack.
  public void push(int n){
    LNode tmp = new LNode(null,n,head);
    if(head == null){ //If nothing is in list.
      tail = tmp;
    }
    else{ //Otherwise
      head.setPrev(tmp);
    }
    head = tmp; //Makes the new element the top of the stack.
  } 
  
  //Removes element from top of stack and returns it.
  public int pop(){
    return dequeue(); 
  }
  
  //Removes element from top of stack and returns it, works with doubly LinkedLists.
  public int dequeue(){
    int n = head.getVal(); //Gets value of the element from the top of the stack.
    if(head == tail){ //Nothing in list.
      head = null;
      tail = null;
    }
    else{
      head = head.getNext(); //Makes the element "next in line" the new head.
      head.setPrev(null); //Sets new top of stack to null.
    }
    return n; //Returns the element from the top of the stack.
  }
  
  //Add element to back of the stack.
  public void enqueue(int n){
    LNode tmp = new LNode(tail,n,null);
    if(tail == null){ //Nothing in list.
      head = tmp;
    }
    else{
      tail.setNext(tmp);
    }
    tail = tmp; //Makes the new element the bottom of the list.
  }
  
  //Delete a LNode reference.
  public void delete(LNode node){
    LNode prev = node.getPrev(); //The previous node to the LNode.
    LNode next = node.getNext(); //The next node to the LNode.
    if(prev == null && next == null){ //Only one thing in list
      head = null;
      tail = null;
    }
    else if(prev == null ){ //If the deleted node is the head.
      dequeue(); 
    }
    else if(next == null){ //If deleted node is the tail.
      tail = prev;
      prev.setNext(next);
    }
    else{ //If in middle of list.
      prev.setNext(next); //Previous node points to next node.
      next.setPrev(prev); //Next node points to previous.
    }
  }
  
  public void delete(int n){ //Delete a node containing a specific interger.
    LNode tmp = head;
    while(tmp != null && tmp.getVal() != n){ 
      tmp = tmp.getNext(); //Progresses through the list until interger spotted or at end of list.
    }
    if(tmp != null){ //If the node stops at interger, delete node. If node stops at end, don't delete anything.
      delete(tmp);
    }
  }
  
  public void deleteAt(int pos){ //Delete a node at a specific position.
    LNode tmp = head;
    for(int i = 0; i < pos; i++){ //Gets to position.
      if(tmp == null){ //If position out of list.
        return;
      }
      tmp = tmp.getNext();
    }
    delete(tmp); //Deletes the node at position
  }
  
  public void sortedInsert(int n){ //Adds a new node at correct pos, assuming list is sorted.
    LNode tmp = head;
    LNode tmp2;
    while(tmp != null && tmp.getVal() >= n){ 
      tmp = tmp.getNext(); //Progresses through the list until interger spotted or at end of list.
    }
    if(tmp == head){ //If correct position is the head.
     push(n);
    }
    else if(tmp == null){ //If correct position is the tail.
      enqueue(n);
    }
    else{ //If correct position is in the middle.
        LNode prev = tmp.getPrev(); //The previous node to the LNode.
        tmp2 = new LNode(prev,n,tmp);
        tmp.setPrev(tmp2);
        tmp.getPrev().getPrev().setNext(tmp2);
      }
    }
  
  public void removeDuplicates(){ //Remove duplicates in the list, leaving only the first iteration of the duplicate.
    LNode tmp = head;
    LNode tmp2;
    while(tmp != null){ //Goes through all the values.
      tmp2 = tmp.getNext(); //2nd variable is the values after tmp.
      while(tmp2 != null){
        if(tmp2.getVal() == tmp.getVal()){ //If the values are the same.
          delete(tmp2); //Delete the node.
        }
        tmp2 = tmp2.getNext(); //2nd variable loops through all the values.
      }
      tmp = tmp.getNext(); //tmp goes through all the values.
    }
  }
  
  public void reverse(){ //Reverses the nodes int the list.
    LNode tmp = head;
    LNode tmp2 = null; 
    LNode tmp3 = null;
    while(tmp != null){
      tmp2 = tmp.getNext(); //This is the next node.
      tmp3 = tmp.getPrev(); //This is the previous node.
      tmp.setPrev(tmp2); //Makes the next node the previous.
      tmp.setNext(tmp3); //Makes the previous node the next.
      tmp = tmp.getPrev(); //Traverse through list.
    }
    tail = head; //Makes head the new tail.
    head = tmp3.getPrev(); //Makes the tail of old list the new head.
  }
  
  public LList clone(){//Returns a new copy of the list.
    LList clone = new LList();//Create a new list.
    LNode tmp = head;
    while(tmp != null){
      clone.enqueue(tmp.getVal());//Adds values from first list to new list.
      tmp = tmp.getNext();
    }
    return clone; //Returns new list.
  }
  
  //toSting method.
  @Override
  public String toString(){
    String ans = "";
    LNode tmp = head;
    while(tmp != null){
      ans += tmp + ", ";
      tmp = tmp.getNext();
    }
    if(ans != ""){
      ans = ans.substring(0, ans.length()-2);
    }
    return "<" + ans + ">";
  }
}
  
class LNode{
  private int val;
  private LNode next;
  private LNode prev;

  public LNode(LNode p, int v, LNode n){
    prev = p;
    val = v;
    next = n;
  }
  
  @Override
  public String toString(){
    return "" + val;
  }
  
  //Getter and Setter Methods.
  public LNode getNext(){
    return next;
  }
  
  public void setNext(LNode n){
    next = n;
  }
  
  public LNode getPrev(){
    return prev;
  }
  
  public void setPrev(LNode p){
    prev = p;
  }
  
  public int getVal(){
    return val; 
  }
  
  public void setVal(int v){
    val = v;
  }   
}