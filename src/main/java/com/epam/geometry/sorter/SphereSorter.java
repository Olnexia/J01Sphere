package com.epam.geometry.sorter;

import com.epam.geometry.comparators.*;
import com.epam.geometry.entity.Sphere;
import com.epam.geometry.exceptions.SphereListSortingException;
import java.util.Collections;
import java.util.List;

public class SphereSorter {

    public void sortSphereList(List<Sphere> sphereList,OrderBy order) throws SphereListSortingException{
        switch (order){
            case ID:
                Collections.sort(sphereList, new IdComparator());
                break;
            case RADIUS:
                Collections.sort(sphereList, new RadiusComparator());
                break;
            case X:
                Collections.sort(sphereList, new XComparator());
                break;
            case Y:
                Collections.sort(sphereList, new YComparator());
                break;
            case Z:
                Collections.sort(sphereList, new ZComparator());
                break;

                default:
                    throw new SphereListSortingException("Sphere list can't be sorted by such order: " + order);

        }
    }
}
