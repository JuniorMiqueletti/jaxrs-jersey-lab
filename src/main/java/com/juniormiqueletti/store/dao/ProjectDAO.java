package com.juniormiqueletti.store.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.juniormiqueletti.store.domain.Project;

public class ProjectDAO {

	private static Map<Long, Project> db = new HashMap<>();
	private static AtomicLong count = new AtomicLong(1);

	static {
		db.put(1l, new Project(1l, "My Store", 2017));
		db.put(2l, new Project(2l, "Free course", 2018));
	}

	public Project find(Long id) {
		return db.get(id);
	}

	public Project remove(long id) {
		return db.remove(id);
	}

	public Map<Long, Project> findAll() {
		return db;
	}

	public void add(Project project) {
		long id = count.incrementAndGet();
		project.setId(id);
		db.put(id, project);
	}

	public Project delete(long id) {
		return db.remove(id);
	}
}
