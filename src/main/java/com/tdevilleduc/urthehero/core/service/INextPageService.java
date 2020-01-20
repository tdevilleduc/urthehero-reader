package com.tdevilleduc.urthehero.core.service;

import com.tdevilleduc.urthehero.core.model.NextPage;

import java.util.List;

public interface INextPageService {

    List<NextPage> findByPageId(Integer pageId);

}
