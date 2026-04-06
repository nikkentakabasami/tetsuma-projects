#ru.alphatech.tm.dao.<m.entity>Dao.java

package ru.alphatech.tm.dao;

import ru.alphatech.utils.dao.SUCrudDao;
import ru.alphatech.utils.misc.SUPage;

public interface <m.entity>Dao extends SUCrudDao {
  
  SUPage\<<m.modelClassName>> find<m.entity>s(<m.entity>Filter filter);
  
}

