package duc.vn.learning.design.patterns;

public class PostStation {
	public static void main(String[] args) {
		Post post = new NormalPost();
		post = post.createPost(post, "Nothing", "Cool here");
		System.out.println(post.getDescription());
	}
}

abstract class Post {
	private PostFactory postFactory = new PostFactoryBehaviour();
	String description = "Empty post";
	public String getDescription() {
		return description;
	}
	
	public Post createPost(Post post, String header, String content) {
		post = postFactory.createHeader(post, header);
		post = postFactory.createContent(post, content);
		post = postFactory.createPictures(post);
		return post;
	}
}

abstract class PostDecorator extends Post {
	Post post;
	public abstract String getDescription();
}

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
	
	public NormalPost() {
		this.description = "Normal post";
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

