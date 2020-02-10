package com.tdevilleduc.urthehero.core.service;

import java.util.List;

public interface ICharacterService {
    List<Integer> findStoryByPersonId(final Integer personId);
}
