RandomAccessFile class allows the dev to access the data of a class at runtime as required, and also to
write data in bytes at specific locations in the file.
With this we can avoid loading the whole locations file at once but rather let it load the locations
that the user is at or moves to.

It allows for this by giving access to move the pointer to a byte location and then carry ot the read/write operation.

Byte locations start at 0, and go till end of file consumed.
If file size comes to be 456bytes, then byte locations will be from 0-455.

Now normally we'd allocate 30 bytes to each location and access them using the formula locBytePos = (locNum-1)30
However that isn't appropriate here as our locations differ in data size and length.

So what we'll do is that we'll create an index of specific length (8bytes, 1long: locBytePos).
Preload it at runtime, and then use its data to load the locations anytime we want.
And we'll write them in order from 0-(totalLocNum-1) so we save on writing the locID or indexNum.

*Note that for this saving of data, we must have all locationIDs as wholeNumbers without any skips*

How we plan to write data: -

1. Starting pos of actual locations' data (8 bytes, 1 long, 0-7)
2. The endIndex, if total number of locations = n, (8n bytes, n long, 8-(8n+7))
3. Actual locations' data.