package me.ynabouzi.miniproject.dao.DAOImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import me.ynabouzi.miniproject.model.NoteEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class NoteEntityDAOImplTest {
	@Mock
	private EntityManager entityManager;

	@InjectMocks
	private NoteEntityDAOImpl noteEntityDAO;

	@Mock
	private EntityTransaction transaction;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(entityManager.getTransaction()).thenReturn(transaction);
	}

	@Test
	public void testGetEntityById() {
		NoteEntity testNoteEntity = new NoteEntity();
		testNoteEntity.setId(1L);

		when(entityManager.find(eq(NoteEntity.class), anyLong())).thenReturn(testNoteEntity);

		NoteEntity result = noteEntityDAO.getEntityById(1L);

		assertEquals(1L, result.getId());
	}

	@Test
	public void testGetAllEntities() {
		NoteEntity note1 = new NoteEntity();
		NoteEntity note2 = new NoteEntity();

		TypedQuery<NoteEntity> query = mock(TypedQuery.class);
		when(entityManager.createQuery("SELECT n FROM NoteEntity n", NoteEntity.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(Arrays.asList(note1, note2));

		List<NoteEntity> result = noteEntityDAO.getAllEntities();

		assertEquals(2, result.size());
	}

	@Test
	public void testSaveEntity() {
		NoteEntity note = new NoteEntity();

		NoteEntity savedNote = noteEntityDAO.saveEntity(note);

		assertEquals(note, savedNote);
		verify(transaction).begin();
		verify(transaction).commit();
	}

	@Test
	public void testUpdateEntity() {
		NoteEntity note = new NoteEntity();
		note.setId(1L);

		when(entityManager.merge(note)).thenReturn(note);

		NoteEntity updatedNote = noteEntityDAO.updateEntity(note, 1L);

		assertEquals(note, updatedNote);
	}

	@Test
	public void testDeleteEntity() {
		NoteEntity note = new NoteEntity();
		note.setId(1L);

		when(noteEntityDAO.getEntityById(1L)).thenReturn(note);

		assertTrue(noteEntityDAO.deleteEntity(1L));
		verify(entityManager).remove(note);
		verify(transaction).begin();
		verify(transaction).commit();

		when(noteEntityDAO.getEntityById(2L)).thenReturn(null);

		assertFalse(noteEntityDAO.deleteEntity(2L));
	}

}