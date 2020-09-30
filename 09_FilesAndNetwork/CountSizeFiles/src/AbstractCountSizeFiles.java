import java.io.File;

public abstract class AbstractCountSizeFiles implements CountSizeFiles {

    protected int countFile = 0;
    protected int countDir = 0;
    protected double sizeFiles = 0;

    public AbstractCountSizeFiles() {
    }

    @Override
    abstract public void countSizeFiles(File file);

    @Override
    abstract public void print();

    @Override
    public void zeroingVariable() {
        countDir = 0;
        countFile = 0;
        sizeFiles = 0;
    }
}
