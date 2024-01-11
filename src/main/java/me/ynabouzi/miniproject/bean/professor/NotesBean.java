package me.ynabouzi.miniproject.bean.professor;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import me.ynabouzi.miniproject.model.NoteEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Named(value = "NotesBean")
@SessionScoped
public class NotesBean implements Serializable {

	private Map<Long, List<NoteEntity>> notesByCourseItem = new HashMap<>();

	@PostConstruct
	public void init() {
		System.out.println("NotesBean init");
		System.out.println("2 : " + notesByCourseItem);
	}

}
