package dao;

import entityClass.Archive;
import exception.BaseException;
import exception.NoUserException;

import java.util.List;

/**
 * @author 卟言呢
 */
public interface ArchiveDao extends BaseDao<Archive> {
    /**
     *@Description 插入一个对象
     *@Param [archive]
     *@Return entityClass.Archive
     *@Exception
     **/
    @Override
    Archive insert(Archive archive);

    /**
     *@Description
     *@Param [archive]
     *@Return entityClass.Archive
     *@Exception
     **/
    @Override
    Archive delete(Archive archive);


    /**
     *@Description
     *@Param [archive]
     *@Return entityClass.Archive
     *@Exception
     **/
    @Override
    Archive findByName(Archive archive) throws BaseException;

    /**
     *@Description 返回所有的对象  
     *@Param null
     *@Return java.util.List<entityClass.Archive>
     *@Exception null
     **/
    @Override
    List<Archive> findAllOnes();

    Archive findById(Long id);
}
