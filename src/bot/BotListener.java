package bot;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter{
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		String message = e.getMessage().getRawContent();
		String author = e.getAuthor().getName();
		
		System.out.println(author+" wrote "+message);
		
		if (author!="FirstBot" && message.startsWith(".")){
			String command=message.substring(1);
			String[] request=command.split(" ");
				
			e.getChannel().sendMessage(e.getAuthor().getAsMention()+" requested "+command).queue();
			if (command.equalsIgnoreCase("help")){
				String response = "This is a bot created by Runar Espelund, you are free to enjoy its many features like:\n\n";
				response += ".help - Lists all available commands and explains their use \n";
				response += ".ping - The bot sends the response pong back \n";
				response += ".pong - The bot sends the response ping back \n";
				response += ".bean - The bot sends a random Mr.Bean gif in the channel \n";
				response += ".robin - The bot sends a popular Robin Aaen meme in the channel \n\n";
				response += "Planned features include the commands: \n\n";
				response += ".leave - The bot leaves the current channel \n";
				response += ".join - The bot joins the channel \n";
				response += ".play <url> - Insert a youtube link that the bot will then proceed to play in the voice channel";
				e.getChannel().sendMessage(response).queue();
			}
			if (command.equalsIgnoreCase("ping")){
				String response = " Pong!";
				e.getChannel().sendMessage(response).queue();
			}
			
			else if (command.equalsIgnoreCase("pong")){
				String response = " Ping!";
				e.getChannel().sendMessage(e.getAuthor().getAsMention()+response).queue();
			}
			
			else if (command.equalsIgnoreCase("robin")) {
				Random rand = new Random();
				int n = rand.nextInt(2)+1;
				String img = "robin/"+n+".jpg";
				try {
					e.getChannel().sendFile(new File(img), null).queue();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			else if (command.equalsIgnoreCase("bean")){
				Random rand = new Random();
				int n = rand.nextInt(61)+1;
				String gif = "bean/"+n+".gif";
				try {
					e.getChannel().sendFile(new File(gif), null).queue();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			else if (request[0].equalsIgnoreCase("play")){
				System.out.println("URL: "+request[1]);
				e.getChannel().sendMessage(author+" requested "+request[1]).queue();
			}
		}
	}
}
