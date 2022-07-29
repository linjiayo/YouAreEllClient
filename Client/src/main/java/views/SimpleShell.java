package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import controllers.IdController;
import controllers.MessageController;
import controllers.TransactionController;
import models.Id;
import models.Message;
import youareell.YouAreEll;

// Simple Shell is a Console view for youareell.YouAreEll.
public class SimpleShell {
    private MessageTextView msgView;
    private IdTextView idView;

    public SimpleShell() {
        msgView = new MessageTextView();
        idView = new IdTextView();
    }
    public static void prettyPrint(String output) {
        // yep, make an effort to format things nicely, eh?
        System.out.println(output);
    }
    public static void main(String[] args) throws java.io.IOException {

        YouAreEll urll = new YouAreEll(new TransactionController(
                new MessageController(), new IdController()));
        SimpleShell shell = new SimpleShell();
        TransactionController tControl = new TransactionController(new MessageController(), new IdController());

        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            System.out.println("Enter a command: ");
            commandLine = console.readLine();

            //input parsed into array of strings(command and arguments)
            String[] commands = commandLine.split(" ");
            List<String> list = new ArrayList<>();

            //if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                list.add(commands[i]);

            }
            System.out.print(list); //***check to see if list was added correctly***
            history.addAll(list);
            //try {
                //display history of shell with index
                if (list.get(list.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                // Specific Commands.

                //TODO refactor
                // ids
                if (list.contains("ids")) {
                    if (list.size() == 1) {
                        List<Id> res = tControl.getIdCtrl().getIdsList();
                        prettyPrint(shell.idView.idsString(res));
                    } else if (list.size() == 3 && list.get(0).equals("ids")) {
                        tControl.getIdCtrl().idPost(new Id(list.get(1), list.get(2)));
                    }
                    System.out.println("Invalid command. Usage:\nids [name] [github_Id]");
                    continue;
                }

                // messages
                if (list.contains("messages")) {
                    if (list.get(0).equals("messages")) {
                        if (list.size() == 1) {
                            List<Message> results = tControl.getMsgCtrl().getMessages();
                            prettyPrint(shell.msgView.messagesString(results));
                        } else if (list.size() == 2) {
                            List<Message> results = tControl.getMsgCtrl().getMessagesForId(new Id(list.get(1)));
                            prettyPrint(shell.msgView.messagesString(results));
                        }
                        continue;
                    }
                    System.out.println("Invalid Command. Usage:\nmessages [github_id] [message] [to] [github-id]");
                    continue;
                }

                if (list.contains("send")) {
                    if (list.size() == 3 && list.get(0).equals("send")) {
                        Id id = new Id(list.get(1));
                        Message msg = new Message(list.get(2), id.getGithub());
                        tControl.getMsgCtrl().postMsgJsonId(id, msg);
                    } else if (list.size() == 5 && list.get(0). equals("send")) {
                        Id fromId = new Id(list.get(1));
                        Message msg = new Message(list.get(2), list.get(1), list.get(4));
                        tControl.getMsgCtrl().postMsgJsonId(fromId, msg);
                    }
                    else {
                        System.out.println("Invalid Command. Usage:\nsend github_id message [to] [github-id]");
                    }
                }
                // you need to add a bunch more.

                //!! command returns the last command in history
                if (list.get(list.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (list.get(list.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(list.get(list.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(list);
                }

                // // wait, wait, what curiousness is this?
                // Process process = pb.start();

                // //obtain the input stream
                // InputStream is = process.getInputStream();
                // InputStreamReader isr = new InputStreamReader(is);
                // BufferedReader br = new BufferedReader(isr);

                // //read output of the process
                // String line;
                // while ((line = br.readLine()) != null)
                //     System.out.println(line);
                // br.close();


            }

            //catch ioexception, output appropriate message, resume waiting for input
//            catch (IOException e) {
//                System.out.println("Input Error, Please try again!");
//            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }

    public MessageTextView getMsgView() {
        return msgView;
    }

    public void setMsgView(MessageTextView msgView) {
        this.msgView = msgView;
    }

    public IdTextView getIdView() {
        return idView;
    }

    public void setIdView(IdTextView idView) {
        this.idView = idView;
    }

    }
