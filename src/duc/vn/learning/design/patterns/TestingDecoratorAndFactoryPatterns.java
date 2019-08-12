/*
 * Problem bayh la cai interface CreatePostFactory hoi bi thua
 * neu de cai createPost o Post class thi cac subclasses aka Decorators va cac Post subclasses khac 
 * deu co the access duoc => hoi bi do? 
 */

package duc.vn.learning.design.patterns;

public class TestingDecoratorAndFactoryPatterns {
	public static void main(String[] args) {
		Post post = new NormalPost();
		PostFactory postFactory = new PostFactoryBehaviour(); // use this so client can choose between different factories
		post = post.createPost(post, postFactory);
		System.out.println(post.getClass().getName());
		System.out.println(post.getDescription());
	}
}

abstract class Post {
	String description = "Empty post";
	public String getDescription() {
		return description;
	}
	
	public Post createPost(Post post, PostFactory postFactory) {
		return null;
	}
}

abstract class PostDecorator extends Post {
	Post post;
	public abstract String getDescription();
}

// use abstract factory pattern - create a family of objects
interface PostFactory {
	Header createHeader(Post post, String header);
	Content createContent(Post post, String content);
	Pictures createPictures(Post post);
}

class PostFactoryBehaviour implements PostFactory {

	@Override
	public Header createHeader(Post post, String header) {
		// TODO Auto-generated method stub
		return new Header(post, header);
	}

	@Override
	public Content createContent(Post post, String content) {
		// TODO Auto-generated method stub
		return new Content(post, content);
	}

	@Override
	public Pictures createPictures(Post post) {
		// TODO Auto-generated method stub
		return new Pictures(post);
	}
	
}

class NormalPost extends Post {
	private String header;
	private String content;
	
	public NormalPost() {
		this.description = "Normal post";
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public Post createPost(Post post, PostFactory postFactory) {
		post = postFactory.createHeader(post, header);
		post = postFactory.createContent(post, content);
		post = postFactory.createPictures(post);
		return post;
	}
}

class Header extends PostDecorator {
	private String header;

	public Header(Post post, String header) {
		this.header = header;
		this.post = post;
	}
	
	public String getHeader() {
		return header;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return post.getDescription() + " " + header;
	}
}

class Content extends PostDecorator {
	private String content;
	public Content(Post post, String content) {
		this.content = content;
		this.post = post;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return post.getDescription() + " " + content;
	}
	
	public String getContent() {
		return content;
	}
	
}

class Pictures extends PostDecorator {
	
	public Pictures(Post post) {
		this.post = post;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return post.getDescription() + " pictures";
	}
	
}

interface Command {
	public void execute();
}

class GarageDoor {
public void up() {
		
	}
	
	public void down() {
		
	}
	
	public void stop() {
		
	}
}

class GarageDoorOpenCommand implements Command {

	GarageDoor garageDoor;
	
	public GarageDoorOpenCommand(GarageDoor door) {
		this.garageDoor = door;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		garageDoor.up();
	}
	
}

