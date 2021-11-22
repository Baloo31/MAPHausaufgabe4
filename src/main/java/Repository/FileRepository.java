package Repository;

import java.io.IOException;
import java.util.List;

public interface FileRepository<T> extends ICrudRepository<T>
{

    void writeData() throws IOException;

    List<T> readData() throws IOException;

}
