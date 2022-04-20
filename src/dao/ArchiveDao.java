package dao;

import entityClass.Archive;
import exception.BaseException;
import exception.NoUserException;

import java.util.List;

/**
 * @author ߲����
 */
public interface ArchiveDao extends BaseDao<Archive> {
    /**
     *@Description ����һ������
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
     *@Description �������еĶ���  
     *@Param null
     *@Return java.util.List<entityClass.Archive>
     *@Exception null
     **/
    @Override
    List<Archive> findAllOnes();

    Archive findById(Long id);
}
