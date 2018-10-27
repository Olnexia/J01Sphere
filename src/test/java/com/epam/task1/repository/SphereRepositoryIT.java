package com.epam.task1.repository;

import com.epam.task1.entity.Point;
import com.epam.task1.entity.Sphere;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SphereRepositoryIT {

    private final static Sphere FIRST_TEST_SPHERE = new Sphere(5 , new Point(5,2,4));
    private final static Sphere SECOND_TEST_SPHERE = new Sphere(3, new Point( 5,2,4));
    private final static long TEST_ID = 42L;
    private Repository<Sphere> repository = new SphereRepository();
    {
        FIRST_TEST_SPHERE.setId(TEST_ID);
        SECOND_TEST_SPHERE.setId(TEST_ID);
        repository.add(FIRST_TEST_SPHERE);
    }

    @Test
    public void shouldFindByVolume(){
        //given
        Specification<Sphere> volumeSpecification = new VolumeGreaterThanSpecification(1);
        //when
        List<Sphere> actual =repository.query(volumeSpecification);
        //then
        Assert.assertEquals(1,actual.size());
        Sphere firstSphere = actual.get(0);
        Assert.assertEquals(FIRST_TEST_SPHERE,firstSphere);
    }

    @Test
    public void shouldFindById(){
        //given
        Specification<Sphere> idSpecification = new IdSpecification(TEST_ID);
        //when
        List<Sphere> actual =repository.query(idSpecification);
        //then
        Assert.assertEquals(1,actual.size());
        Sphere firstSphere = actual.get(0);
        Assert.assertEquals(FIRST_TEST_SPHERE,firstSphere);
    }

    @Test
    public void shouldUpdateWithSameId(){
        //given
        Specification<Sphere> idSpecification = new IdSpecification(TEST_ID);
        //when
        repository.update(SECOND_TEST_SPHERE);
        //then
        List<Sphere> actual = repository.query(idSpecification);
        Assert.assertEquals(1,actual.size());
        Sphere actualSphere = actual.get(0);
        Assert.assertEquals(SECOND_TEST_SPHERE,actualSphere);
    }

    @Test
    public void shouldReturnEmptyListWhenSphereDeleted(){
        //given
        Specification<Sphere> idSpecification = new IdSpecification(TEST_ID);
        //when
        repository.remove(SECOND_TEST_SPHERE);
        //then
        List<Sphere> actual = repository.query(idSpecification);
        Assert.assertEquals(0,actual.size());
    }

}
