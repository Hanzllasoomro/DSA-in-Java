import jdk.dynalink.support.ChainedCallSite;

public class WebStack {
    LinkedListStack history;
    int numberofPage;
    WebStack(){
        history = new LinkedListStack();
        numberofPage = -1;
    }

    void viewPage(String url){
        while (numberofPage < history.getSize() -1)
            history.delete();
        history.push(url);
        numberofPage++;
    }

    String getCurrentPage() {
        if (numberofPage >= 0 && numberofPage < history.getSize()) {
            return history.peek().toString();
        } else {
            return "No page available";
        }
    }
    public String backward() {
        if (numberofPage > 0) {
            numberofPage--;
            return history.peek().toString();
        } else {
            return "No previous page";
        }
    }

    public String Forward() {
        if (numberofPage < history.getSize()- 1) {
            numberofPage++;
            return history.peek().toString();
        } else {
            return "No next page";
        }
    }

    public static void main(String[] args) {
        var browser = new WebStack();

        browser.viewPage("https://www.geeksforgeeks.org/");
        browser.viewPage("https://www.google.com/");
        browser.viewPage("https://pk.linkedin.com/");

        System.out.println("Current Page: " + browser.getCurrentPage());

        System.out.println("Going Forward : " + browser.Forward());

        System.out.println("Going Backward : " + browser.backward());

    }

}
