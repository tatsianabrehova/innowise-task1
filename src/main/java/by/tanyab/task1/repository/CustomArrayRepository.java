package by.tanyab.task1.repository;

import by.tanyab.task1.entity.CustomArray;
import by.tanyab.task1.specification.CustomArraySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomArrayRepository {
    private static final Logger logger = LogManager.getLogger(CustomArrayRepository.class);
    private static CustomArrayRepository instance;
    private final List<CustomArray> arrays = new ArrayList<>();

    private CustomArrayRepository() {
        logger.debug("CustomArrayRepository created");
    }

    public static CustomArrayRepository getInstance() {
        if (instance == null) {
            synchronized (CustomArrayRepository.class) {
                if (instance == null) {
                    instance = new CustomArrayRepository();
                }
            }
        }
        return instance;
    }

    public boolean add(CustomArray array) {
        logger.debug("Adding array with id={}", array.getId());
        return arrays.add(array);
    }

    public boolean addElement(CustomArray array) {
        return add(array);
    }

    public boolean remove(CustomArray array) {
        logger.debug("Removing array with id={}", array.getId());
        return arrays.remove(array);
    }

    public boolean removeElement(CustomArray array) {
        return remove(array);
    }

    public void sort(Comparator<CustomArray> comparator) {
        List<CustomArray> copy = new ArrayList<>(arrays);
        copy.sort(comparator);
        arrays.clear();
        arrays.addAll(copy);
        logger.debug("Sorting completed, arrays count: {}", arrays.size());
    }

    public List<CustomArray> query(CustomArraySpecification specification) {
        logger.debug("Querying arrays with specification");
        List<CustomArray> result = new ArrayList<>();
        for (CustomArray array : arrays) {
            if (specification.specify(array)) {
                result.add(array);
            }
        }
        logger.debug("Query result: {} arrays found", result.size());
        return result;
    }

    public List<CustomArray> queryStream(CustomArraySpecification specification) {
        logger.debug("Querying arrays with specification using stream");
        return arrays.stream()
                .filter(specification::specify)
                .collect(Collectors.toList());
    }

    public CustomArray findById(Long id) {
        logger.debug("Finding array by id={}", id);
        return arrays.stream()
                .filter(array -> array.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<CustomArray> findBySize(int size) {
        logger.debug("Finding arrays by size={}", size);
        return arrays.stream()
                .filter(array -> array.getSize() == size)
                .collect(Collectors.toList());
    }

    public int size() {
        return arrays.size();
    }

    public int getSize() {
        return size();
    }

    public boolean isEmpty() {
        return arrays.isEmpty();
    }

    public boolean hasNoElements() {
        return isEmpty();
    }

    public boolean contains(CustomArray array) {
        return arrays.contains(array);
    }

    public boolean containsElement(CustomArray array) {
        return contains(array);
    }
}