package com.fpineda.samples.choreographypattern.core.ports;

import com.fpineda.samples.choreographypattern.core.model.Product;

public interface FetchProductPort {

    Product fetch(long id);
}
